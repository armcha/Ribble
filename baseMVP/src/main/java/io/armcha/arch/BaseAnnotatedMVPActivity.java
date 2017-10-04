package io.armcha.arch;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

/**
 * Created by Chatikyan on 29.06.2017.
 */

public abstract class BaseAnnotatedMVPActivity<V extends BaseMVPContract.View, P extends BaseMVPContract.Presenter<V>> extends BaseMVPActivity<V, P> {

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getClass().getAnnotation(Viewable.class).layout();
        if (layoutResId == Viewable.LAYOUT_NOT_DEFINED)
            throw new MvpException("Can't find layout res Id");
        setContentView(layoutResId);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected P initPresenter() {
        return (P) AnnotationHelper.createPresenter(getClass());
    }
}
