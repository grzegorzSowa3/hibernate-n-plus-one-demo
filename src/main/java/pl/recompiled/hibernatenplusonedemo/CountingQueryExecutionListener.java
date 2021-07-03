package pl.recompiled.hibernatenplusonedemo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("integration-test")
@Slf4j
@RequiredArgsConstructor
public class CountingQueryExecutionListener implements QueryExecutionListener {

    @Getter
    private Integer count = 0;

    @Override
    public void beforeQuery(ExecutionInfo executionInfo, List<QueryInfo> list) {

    }

    @Override
    public void afterQuery(ExecutionInfo executionInfo, List<QueryInfo> list) {
        count = count + 1;
    }

    public void resetCount() {
        count = 0;
    }
}
