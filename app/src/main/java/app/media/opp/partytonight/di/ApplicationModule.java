package app.media.opp.partytonight.di;

import android.content.Context;
import android.util.Log;

import javax.inject.Singleton;

import app.media.opp.partytonight.data.di.DataModule;
import app.media.opp.partytonight.domain.schedulers.ObserveOn;
import app.media.opp.partytonight.domain.schedulers.SubscribeOn;
import app.media.opp.partytonight.presentation.PartyTonightApplication;
import app.media.opp.partytonight.presentation.utils.FileUtils;
import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module(includes = DataModule.class)
public class ApplicationModule {
    private final PartyTonightApplication application;

    public ApplicationModule(PartyTonightApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        Log.e("module", "app");
        return application;
    }

    @Provides
    @Singleton
    FileUtils provideFileUtils(Context context) {
        return new FileUtils(context);
    }

    @Provides
    @Singleton
    SubscribeOn provideSubscribeOn() {
        return Schedulers::newThread;
    }

    @Singleton
    @Provides
    ObserveOn provideObserveOn() {
        return AndroidSchedulers::mainThread;
    }
}
