package earstone.springcoreadvanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    private ThreadLocal<String> nameStores = new ThreadLocal<>();

    public String logic(String name) {
        log.info("저장 name={} --> nameStore={}", name, nameStores.get());
        nameStores.set(name);
        sleep(1000);
        log.info("조회 nameStore={}", nameStores.get());

        return nameStores.get();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
