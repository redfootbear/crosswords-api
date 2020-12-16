package redfootbear.neo4j;

import io.quarkus.test.junit.QuarkusTest;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.inject.Stereotype;
import org.junit.jupiter.api.extension.ExtendWith;

@Stereotype
@QuarkusTest
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(Neo4jEmbeddedExtension.class)
public @interface Neo4jEmbedded {

}
