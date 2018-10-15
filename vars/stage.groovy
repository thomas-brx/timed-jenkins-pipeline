#!/usr/bin/env groovy

import com.timgroup.statsd.StatsDClient;
import com.timgroup.statsd.NonBlockingStatsDClient;

@NonCPS
void log(String tag) {
    echo tag
    StatsDClient statsd = new NonBlockingStatsDClient("jenkins.stats", "localhost", 8125);
    statsd.incrementCounter(tag);
}

def stage(String name, Closure c) {
    echo "Calling ${name}"
    return steps.stage(name, { zap("${name}.pre") ;c.call(); zap("${name}.post"); })
}
