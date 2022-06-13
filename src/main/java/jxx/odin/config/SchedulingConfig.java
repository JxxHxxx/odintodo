package jxx.odin.config;


import jxx.odin.domain.mission.MissionCycle;
import jxx.odin.domain.schedule.MissionScheduleManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.LocalTime;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfig implements SchedulingConfigurer {

    private final MissionScheduleManager missionScheduleManager;
    private final int POOL_SIZE = 4;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.initialize();

        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }


    @Scheduled(cron = "0/5 * * * * *")
    public void initTestScheduler() {
        log.info("스케줄링 작업이 정상적으로 작동합니다. 현재 시간 [{}]", LocalTime.now());
    }

    @Scheduled(cron = "0 0 4 1/1 * *")
    public void initDailyMission() {
        log.info("일간 미션 초기화를 진행합니다.");
        missionScheduleManager.initMission(MissionCycle.DAY);
    }

    @Scheduled(cron = "0 0 4 ? * MON")
    public void initWeeklyMission() {
        log.info("주간 미션 초기화를 진행합니다.");
        missionScheduleManager.initMission(MissionCycle.WEEKEND);
    }
}
