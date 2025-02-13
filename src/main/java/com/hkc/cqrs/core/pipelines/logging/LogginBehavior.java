package com.hkc.cqrs.core.pipelines.logging;

import an.awesome.pipelinr.Command;
import org.springframework.stereotype.Component;

@Component
public class LogginBehavior implements Command.Middleware{

    @Override
    public <R, C extends Command<R>> R invoke(C c, Next<R> next) {

        long startTime = System.currentTimeMillis();

        R result = next.invoke();

        long endTime = System.currentTimeMillis();

        System.out.println(c.getClass().getName() + " komutu " + (endTime - startTime)+" ms sürede tanıımlandı ");
        return result;
    }
}
