package io.armcha.arch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Chatikyan on 29.06.2017.
 */

public class BaseAnnotatedMVPFragment<V extends BaseMVPContract.View, P extends BaseMVPContract.Presenter<V>>
        extends BaseMVPFragment<V, P> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutResId = getClass().getAnnotation(Viewable.class).layout();
        if (layoutResId == Viewable.LAYOUT_NOT_DEFINED)
            throw new MvpException("Can't find layout res Id");
        return inflater.inflate(layoutResId, container, false);

    }

    @SuppressWarnings("unchecked")
    @Override
    protected P initPresenter() {
        return (P) AnnotationHelper.createPresenter(getClass());
    }
}
