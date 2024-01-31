package the.oronco.springintegration.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@ConfigurationProperties("the.oronco")
@Validated
public record CustomProps(@DefaultValue("false") boolean yes,
                          @NotEmpty @DefaultValue("nothing") String whatIsThis,
                          @Valid @DefaultValue Nested nested,
                          @NotEmpty @DefaultValue("pong") String pongMessage,
                          Map<@NotEmpty String, @NotNull Boolean> mappedy) {

    private enum BirbMode {
        FLYING,
        WALKING,
        SLEEPING,
        EEPING,
    }

    public record Nested(@DefaultValue("greeboo") String birbName,
                         @Min(0) @DefaultValue("69") int birbAge,
                         @DefaultValue("EEPING") BirbMode birbMode) {


    }
}
