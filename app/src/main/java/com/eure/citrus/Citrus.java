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
                "2d2329a32f2b834527eb0d2a301032f2a27df4dd6814f95cb7e966a6f832020c",
                "cbf48ac77f3edfff2319c4185d7def9e11aa62d38b6bd5c38570d114bf25afb9");
    }

}
