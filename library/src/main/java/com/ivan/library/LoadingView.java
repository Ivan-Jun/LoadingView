package com.ivan.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * 视图加载状态切换控件,方便加载过程控制
 *
 * @author Ivan
 */
public class LoadingView extends ViewSwitcher {

    /**
     * 加载中
     */
    public final static int LOADING = 0;
    /**
     * 加载失败
     */
    public final static int NET_ERROR = 1;
    /**
     * 加载出错
     */
    public final static int ERROR = 2;
    /**
     * 数据为空
     */
    public final static int EMPTY = 3;
    /**
     * 正常状态
     */
    public final static int NORMAL = 4;

    private InnerView loadingView;
    private int icon_error;
    private int icon_empty;
    private int icon_error_net;
    private String text_error;
    private String text_empty;
    private String text_error_net;

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.LoadingView);
        icon_error = a.getResourceId(R.styleable.LoadingView_icon_error, R.mipmap.icon_net_error);
        icon_empty = a.getResourceId(R.styleable.LoadingView_icon_empty, R.mipmap.icon_empty);
        icon_error_net = a.getResourceId(R.styleable.LoadingView_icon_error_net, R.mipmap.icon_server_error);
        text_error = a.getString(R.styleable.LoadingView_text_error);
        text_empty = a.getString(R.styleable.LoadingView_text_empty);
        text_error_net = a.getString(R.styleable.LoadingView_text_error_net);
        init();
    }

    private void init() {
        loadingView = new InnerView(getContext());
        addView(loadingView);
        setLoadingState(LOADING);
        setDrawableEmptyRes(icon_empty);
        setDrawableNetError(icon_error_net);
        setDrawableServerError(icon_error);
        if (!isEmpty(text_error)) {
            setTextServerError(text_error);
        }
        if (!isEmpty(text_empty)) {
            setTextEmpty(text_empty);
        }
        if (!isEmpty(text_error_net)) {
            setTextNetError(text_error_net);
        }
    }

    public void setLoadingState(int state) {
        switch (state) {
            case LOADING:
                if ((getCurrentView() != loadingView)) {
                    showNext();
                }
                loadingView.showView(state);
                break;
            case ERROR:
                if ((getCurrentView() != loadingView)) {
                    showNext();
                }
                loadingView.showView(state);
                break;
            case NET_ERROR:
                if ((getCurrentView() != loadingView)) {
                    showNext();
                }
                loadingView.showView(state);
                break;
            case EMPTY:
                if ((getCurrentView() != loadingView)) {
                    showNext();
                }
                loadingView.showView(state);
                break;
            case NORMAL:
                if ((getCurrentView() == loadingView)) {
                    showPrevious();
                }
                break;
        }
    }

    public int getLoadingState() {
        return loadingView.getState();
    }

    public void setTextEmpty(int msgId) {
        loadingView.setTextEmpty(msgId);
    }

    public void setTextEmpty(CharSequence msg) {
        loadingView.setTextEmpty(msg);
    }

    /**
     * 网络异常展示文字
     *
     * @param msg
     */
    public void setTextNetError(CharSequence msg) {
        loadingView.setTextNetError(msg);
    }

    /**
     * 网络异常展示文字
     *
     * @param msgId
     */
    public void setTextNetError(int msgId) {
        loadingView.setTextNetError(msgId);
    }

    /**
     * 服务器异常展示文字
     *
     * @param msg
     */
    public void setTextServerError(CharSequence msg) {
        loadingView.setTextServerError(msg);
    }

    /**
     * 服务器异常展示文字
     *
     * @param msgId
     */
    public void setTextServerError(int msgId) {
        loadingView.setTextServerError(msgId);
    }

    public void setDrawableNetError(int drawableId) {
        loadingView.setDrawableNetError(drawableId);
    }

    public void setDrawableServerError(int drawableId) {
        loadingView.setDrawableServerError(drawableId);
    }

    public void setButtonText(int msgId) {
        loadingView.setButtonText(msgId);
    }

    public void setButtonText(CharSequence msg) {
        loadingView.setButtonText(msg);
    }

    public void setOnErrorClickListener(OnErrorClickListener onErrorClickListener) {
        loadingView.setOnErrorClickListener(onErrorClickListener);
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        loadingView.setOnButtonClickListener(onButtonClickListener);
    }

    public void setButtonVisible(int visible) {
        loadingView.setButtonVisible(visible);
    }

    public void setDrawableEmptyRes(int drawableId) {
        loadingView.setDrawableEmptyRes(drawableId);
    }

    public interface OnErrorClickListener {
        void onErrorClick();
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }

    private class InnerView extends RelativeLayout {

        private OnErrorClickListener onErrorClickListener;
        private OnButtonClickListener onButtonClickListener;

        private int state;

        public InnerView(Context context) {
            super(context);
            setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            LayoutInflater.from(context).inflate(R.layout.view_loading, this, true);
            init();
        }

        private void init() {
            findViewById(R.id.view_loading_error).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onErrorClickListener != null) {
                        showView(LOADING);
                        onErrorClickListener.onErrorClick();
                    }
                }
            });
            findViewById(R.id.view_loading_net_error).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onErrorClickListener != null) {
                        showView(LOADING);
                        onErrorClickListener.onErrorClick();
                    }
                }
            });
            findViewById(R.id.btn_empty).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onButtonClickListener != null) {
                        onButtonClickListener.onButtonClick();
                    }
                }
            });
        }

        /**
         * 空数据展示文字
         *
         * @param msgId
         */
        public void setTextEmpty(int msgId) {
            ((TextView) findViewById(R.id.tv_loading_empty)).setText(msgId);
        }

        /**
         * 空数据展示文字
         *
         * @param msg
         */
        public void setTextEmpty(CharSequence msg) {
            ((TextView) findViewById(R.id.tv_loading_empty)).setText(msg);
        }

        /**
         * 网络异常展示文字
         *
         * @param msg
         */
        public void setTextNetError(CharSequence msg) {
            ((TextView) findViewById(R.id.tv_net_error)).setText(msg);
        }

        /**
         * 网络异常展示文字
         *
         * @param msgId
         */
        public void setTextNetError(int msgId) {
            ((TextView) findViewById(R.id.tv_net_error)).setText(msgId);
        }

        /**
         * 服务器异常展示文字
         *
         * @param msg
         */
        public void setTextServerError(CharSequence msg) {
            ((TextView) findViewById(R.id.tv_error)).setText(msg);
        }

        /**
         * 服务器异常展示文字
         *
         * @param msgId
         */
        public void setTextServerError(int msgId) {
            ((TextView) findViewById(R.id.tv_error)).setText(msgId);
        }

        public void setDrawableEmptyRes(int drawableId) {
            ((ImageView) findViewById(R.id.img_empty)).setImageResource(drawableId);
        }

        public void setDrawableNetError(int drawableId) {
            ((ImageView) findViewById(R.id.img_net_error)).setImageResource(drawableId);
        }

        public void setDrawableServerError(int drawableId) {
            ((ImageView) findViewById(R.id.img_server_error)).setImageResource(drawableId);
        }

        public void setButtonText(int msgId) {
            ((TextView) findViewById(R.id.btn_empty)).setText(msgId);
        }

        public void setButtonText(CharSequence msg) {
            ((TextView) findViewById(R.id.btn_empty)).setText(msg);
        }

        public void setButtonVisible(int visible) {
            findViewById(R.id.btn_empty).setVisibility(visible);
        }

        public void showView(int state) {
            this.state = state;
            switch (state) {
                case LOADING:
                    findViewById(R.id.view_loading_default).setVisibility(View.VISIBLE);
                    findViewById(R.id.view_loading_error).setVisibility(View.GONE);
                    findViewById(R.id.view_loading_net_error).setVisibility(View.GONE);
                    findViewById(R.id.view_loading_empty).setVisibility(View.GONE);
                    break;
                case ERROR:
                    findViewById(R.id.view_loading_default).setVisibility(View.GONE);
                    findViewById(R.id.view_loading_error).setVisibility(View.VISIBLE);
                    findViewById(R.id.view_loading_net_error).setVisibility(View.GONE);
                    findViewById(R.id.view_loading_empty).setVisibility(View.GONE);
                    break;
                case NET_ERROR:
                    findViewById(R.id.view_loading_default).setVisibility(View.GONE);
                    findViewById(R.id.view_loading_error).setVisibility(View.GONE);
                    findViewById(R.id.view_loading_net_error).setVisibility(View.VISIBLE);
                    findViewById(R.id.view_loading_empty).setVisibility(View.GONE);
                    break;
                case EMPTY:
                    findViewById(R.id.view_loading_default).setVisibility(View.GONE);
                    findViewById(R.id.view_loading_error).setVisibility(View.GONE);
                    findViewById(R.id.view_loading_net_error).setVisibility(View.GONE);
                    findViewById(R.id.view_loading_empty).setVisibility(View.VISIBLE);
                    break;
            }
        }

        public void setOnErrorClickListener(OnErrorClickListener onErrorClickListener) {
            this.onErrorClickListener = onErrorClickListener;
        }

        public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
            this.onButtonClickListener = onButtonClickListener;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

    }

    public boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

}
