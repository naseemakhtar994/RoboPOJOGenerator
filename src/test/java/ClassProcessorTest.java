import com.robohorse.robopojogenerator.generator.ClassItem;
import com.robohorse.robopojogenerator.generator.processors.ClassProcessor;
import com.robohorse.robopojogenerator.generator.utils.ClassGenerateHelper;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utils.JsonReader;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by vadim on 02.10.16.
 */

public class ClassProcessorTest {
    private JsonReader jsonReader = new JsonReader();

    @InjectMocks
    ClassProcessor classProcessor;
    @Mock
    ClassGenerateHelper classGenerateHelper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSingleObjectGeneration_isCorrect() throws Exception {
        final JSONObject jsonObject = jsonReader.read("single_object.json");
        final String name = "Response";
        final Set<ClassItem> classItemSet = new HashSet<>();

        Mockito.when(classGenerateHelper.getClassName(name))
                .thenReturn(name);

        classProcessor.proceed(jsonObject, name, classItemSet);
        assertTrue(classItemSet.size() == 1);

        Iterator iterator = classItemSet.iterator();
        ClassItem classItem = (ClassItem) iterator.next();
        assertEquals(classItem.getClassName(), name);

        final Map<String, String> fields = classItem.getClassFields();
        assertNotNull(fields);

        for (String key : jsonObject.keySet()) {
            assertTrue(fields.containsKey(key));
        }
    }
}
