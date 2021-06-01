package org.springframework.samples.petclinic.rewrite;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolConfig {
    @Bean
    public DogSchool dogSchool() {
        return new DogSchool();
    }

    @Bean
    public CatSchool catSchool() {
        return new CatSchool();
    }

    @Bean
    @Conditional(ConditionCatSchoolOrDogSchool.class)
    public VetSchool vetSchool() {
        return new VetSchool();
    }

    private static class ConditionCatSchoolOrDogSchool extends AnyNestedCondition {
        ConditionCatSchoolOrDogSchool() {
            super(ConfigurationPhase.REGISTER_BEAN);
        }

        @ConditionalOnBean(CatSchool.class)
        class CatSchoolCondition {
        }

        @ConditionalOnBean(DogSchool.class)
        class DogSchoolCondition {
        }
    }
}
