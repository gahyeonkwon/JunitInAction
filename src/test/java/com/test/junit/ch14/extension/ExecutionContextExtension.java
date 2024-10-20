package com.test.junit.ch14.extension;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;
import java.util.Properties;

/**
 *   - 승객수에 따라 low, regular, peak 로 구분된 세가지 Context를 정의
 *   - 혼잡 콘텍스에서는 테스트를 실행하지 않는 Extension 구현
 */
public class ExecutionContextExtension implements ExecutionCondition { // ExcutionConditin은 Junit.jupiter에 포함되어 있음

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {
        Properties properties = new Properties();
        String excutionContext = "";

        try {

            // context 설정값은 임의로 정의되어 있다고 가정한다
            properties.load(ExecutionContextExtension.class.getClassLoader().getResourceAsStream("context.properties")); // context.properties 파일 load

        excutionContext = properties.getProperty("context"); // context 항목 읽어오기

        // context 가 peak 면 실행하지 않고, regular나 low일 때는 실행한다
        if(!"regular".equalsIgnoreCase(excutionContext) && !"low".equalsIgnoreCase(excutionContext)) {
            return ConditionEvaluationResult.disabled("Test disabled outside regular and low contexts");
        }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ConditionEvaluationResult 메서드는 테스트를 활성화할지 말지 결정한다
        return ConditionEvaluationResult.enabled("Test enabled on the " + excutionContext + " context");
    }
}
