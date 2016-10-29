package app.media.opp.partytonight.domain.schedulers;

import rx.Scheduler;

public interface ObserveOn {
    Scheduler getScheduler();
}
