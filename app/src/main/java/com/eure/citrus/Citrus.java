package com.eure.citrus;

import com.eure.citrus.model.repository.GroupRepository;
import com.nifty.cloud.mb.NCMB;
import com.squareup.leakcanary.LeakCanary;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by katsuyagoto on 15/06/18.
 */
public class Citrus extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        initRealm();
        initCalligraphy();
        initNCMB();
    }


    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getApplicationContext())
                .name(getString(R.string.db_name))
                .schemaVersion(getResources().getInteger(R.integer.db_version))
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Realm realm = Realm.getDefaultInstance();
        GroupRepository
                .createDefaultGroups(realm);
        realm.close();
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath(getString(R.string.font_roboto_light))
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    private void initNCMB() {
        NCMB.initialize(getApplicationContext(),
                "7a5bde3c43ed896eea5ff46dad6b75a71666b4e6e77a60395988d4c304fa8ca6",
                "746f9ab69d6cba3c792f391323581f8a097f0aaf95d31a14e67bebedcc385a16");
    }

}
