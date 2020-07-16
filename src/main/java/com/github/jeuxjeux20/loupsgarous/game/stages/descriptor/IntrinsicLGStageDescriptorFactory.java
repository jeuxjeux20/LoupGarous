package com.github.jeuxjeux20.loupsgarous.game.stages.descriptor;

import com.github.jeuxjeux20.loupsgarous.game.OrchestratorScoped;
import com.github.jeuxjeux20.loupsgarous.game.stages.LGStage;
import com.github.jeuxjeux20.loupsgarous.game.stages.StageInfo;
import org.apache.commons.lang.StringUtils;

import java.util.function.Consumer;

@OrchestratorScoped
public class IntrinsicLGStageDescriptorFactory
        implements LGStageDescriptor.Factory {
    @Override
    public LGStageDescriptor create(Class<? extends LGStage> stageClass) {
        LGStageDescriptor descriptor = new LGStageDescriptor(stageClass);

        applyAnnotation(stageClass, descriptor);

        return descriptor;
    }

    private void applyAnnotation(Class<? extends LGStage> stageClass, LGStageDescriptor descriptor) {
        StageInfo annotation = stageClass.getAnnotation(StageInfo.class);
        if (annotation != null) {
            whenNotEmpty(annotation.name(), descriptor::setName);
            whenNotEmpty(annotation.title(), descriptor::setTitle);
            descriptor.setTemporary(annotation.isTemporary());
            descriptor.setColor(annotation.color());
        }
    }

    private void whenNotEmpty(String value, Consumer<String> valueSetter) {
        if (StringUtils.isNotEmpty(value)) {
            valueSetter.accept(value);
        }
    }
}