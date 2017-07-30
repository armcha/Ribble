package com.luseen.ribble.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luseen.logger.Logger;
import com.luseen.ribble.App;
import com.luseen.ribble.R;
import com.luseen.ribble.data.entity.Shot;
import com.luseen.ribble.data.network.Api;
import com.luseen.ribble.data.network.ApiService;
import com.luseen.ribble.data.pref.Preferences;
import com.luseen.ribble.presentation.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends BaseActivity {

    @Inject
    protected ApiService apiService;

    @Inject
    protected Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getAppComponent().inject(this);

        Logger.log(apiService);
        Logger.log(preferences);
        apiService.getShots(20,"c14f402db06bc15be330b7a4fd049d1ca88fe5fa23892c073ca97dc422cfe9ee")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Shot>>() {
                    @Override
                    public void accept(List<Shot> o) throws Exception {
                        Logger.log(o.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.log(throwable);
                    }
                });
    }
}
