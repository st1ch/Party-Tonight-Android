package app.media.opp.partytonight.domain.schedulers;

import rx.Scheduler;

public interface SubscribeOn {
    Scheduler getScheduler();
}
