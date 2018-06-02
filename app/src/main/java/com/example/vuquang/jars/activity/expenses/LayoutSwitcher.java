package com.example.vuquang.jars.activity.expenses;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuquang.jars.R;


/**
 * Created by ThangN on 10/21/2015.
 */
public class LayoutSwitcher {
    public static final int LOADING_MODE = 0;
    public static final int ERROR_MODE = 1;
    public static final int DATA_MODE = 2;
    public static final int BLANK_MODE = 3;
    public static final int EMPTY_MODE = 4;
    protected final View mContentLayout;
    private final int mErrorLayerId;
    private final Handler mHandler;
    private final int mLoadingLayerId;
    private final RetryButtonListener mRetryListener;
    private final View.OnClickListener retryClickListener;
    protected int mDataLayerId;
    private int emptyLayerId;
    private int mMode;
    private volatile boolean mPendingLoad;

    public LayoutSwitcher(View pageLayout, int dataLayerId, int errorLayerId, int loadingLayerId, RetryButtonListener listener, int initialState) {
        this.mHandler = new Handler();
        this.retryClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                LayoutSwitcher.this.switchToLoadingMode();
                LayoutSwitcher.this.mRetryListener.onRetry();
            }
        };
        this.mPendingLoad = false;
        this.mDataLayerId = dataLayerId;
        this.mErrorLayerId = errorLayerId;
        this.mLoadingLayerId = loadingLayerId;
        this.mContentLayout = pageLayout;
        this.mRetryListener = listener;
        this.mMode = initialState;
    }

    public LayoutSwitcher(View pageLayout, int dataLayerId, RetryButtonListener listener) {
        this.mHandler = new Handler();
        this.retryClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                LayoutSwitcher.this.switchToLoadingMode();
                if (mRetryListener != null)
                    LayoutSwitcher.this.mRetryListener.onRetry();
            }
        };
        this.mPendingLoad = false;
        this.mDataLayerId = dataLayerId;
        this.mErrorLayerId = R.id.layout_error;
        this.mLoadingLayerId = R.id.layout_loading;
        this.mContentLayout = pageLayout;
        this.mRetryListener = listener;
        resetMode();
    }

    public LayoutSwitcher(View pageLayout, int dataLayerId, int emptyLayerId, RetryButtonListener listener) {
        this(pageLayout, dataLayerId, listener);
        this.emptyLayerId = emptyLayerId;
    }

    public boolean isDataMode() {
        return this.mMode == DATA_MODE;
    }

    public void switchToBlankMode() {
        performSwitch(BLANK_MODE, null);
    }

    public void switchToLoadingMode() {
        performSwitch(LOADING_MODE, null);
    }

    public void switchToLoadingDelayed(int delayMillis) {
        this.mPendingLoad = true;
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (LayoutSwitcher.this.mPendingLoad) {
                    LayoutSwitcher.this.switchToLoadingMode();
                }
            }
        }, (long) delayMillis);
    }

    public void switchToDataMode() {
        performSwitch(DATA_MODE, null);
    }

    public void switchToDataMode(int dataLayerId) {
        this.mDataLayerId = dataLayerId;
        switchToDataMode();
    }

    public void switchToErrorMode(String error) {
        performSwitch(ERROR_MODE, error);
    }

    protected void setDataVisible(boolean show, boolean goingToLoadingMode) {
        if (this.mDataLayerId > 0) {
            ViewGroup dataView = (ViewGroup) this.mContentLayout.findViewById(this.mDataLayerId);
            if (dataView != null) {
                dataView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        }
    }

    private void setEmptyVisible(boolean show) {
        if (this.emptyLayerId > 0) {
            ViewGroup v = (ViewGroup) this.mContentLayout.findViewById(this.emptyLayerId);
            if (v != null) {
                v.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        }
    }

    private void resetMode() {
        this.mMode = BLANK_MODE;
        setLoadingVisible(false);
        setErrorVisible(false, null);
        setDataVisible(false, false);
        setEmptyVisible(false);
    }

    private void performSwitch(int newMode, String errorMessage) {
        this.mPendingLoad = false;
        if (this.mMode != newMode) {
//			switch (this.mMode) {
//				case LOADING_MODE:
//					setLoadingVisible(false);
//					break;
//				case ERROR_MODE:
//					setErrorVisible(false, null);
//					break;
//				case DATA_MODE:
//					setDataVisible(false, newMode == 0);
//					break;
//			}
            setLoadingVisible(false);
            setErrorVisible(false, null);
            setDataVisible(false, newMode == LOADING_MODE);
            setEmptyVisible(false);
            switch (newMode) {
                case LOADING_MODE:
                    setLoadingVisible(true);
                    break;
                case ERROR_MODE:
                    setErrorVisible(true, errorMessage);
                    break;
                case DATA_MODE:
                    setDataVisible(true, false);
                    break;
                case BLANK_MODE:
                    break;
                case EMPTY_MODE:
                    setEmptyVisible(true);
                    break;
                default:
                    throw new IllegalStateException("Invalid mode " + newMode + "should be LOADING_MODE, ERROR_MODE, DATA_MODE, or BLANK_MODE");
            }
            this.mMode = newMode;
        }
    }

    private void setLoadingVisible(boolean show) {
        this.mContentLayout.findViewById(this.mLoadingLayerId).setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void setErrorVisible(boolean show, String errorMessage) {
        View errorIndicator = this.mContentLayout.findViewById(this.mErrorLayerId);
        errorIndicator.setVisibility(show ? View.VISIBLE : View.GONE);
        if (show) {
            ((TextView) errorIndicator.findViewById(R.id.txt_msg)).setText(errorMessage);
        }
        errorIndicator.findViewById(R.id.layout_content_msg).setOnClickListener(show ? this.retryClickListener : null);
    }

    public void switchToEmptyMode() {
        performSwitch(EMPTY_MODE, null);
    }

    public void switchToEmptyMode(String msg, int placeHolder) {
        if (this.emptyLayerId > 0) {
            ViewGroup v = (ViewGroup) this.mContentLayout.findViewById(this.emptyLayerId);
            if (v != null) {
                TextView txtMsg = (TextView) v.findViewById(R.id.txt_empty_msg);
                if (txtMsg != null) {
                    txtMsg.setText(msg);
                }
                ImageView img = (ImageView) v.findViewById(R.id.img_empty);
                if (img != null) {
                    img.setImageResource(placeHolder);
                }
            }
        }
        performSwitch(EMPTY_MODE, null);
    }

    public interface RetryButtonListener {
        void onRetry();
    }
}
