import nodes.*;
import visual.scripting.GraphWindow;
import visual.scripting.VisualScriptingPlugin;

public class ScriptMaths extends VisualScriptingPlugin {

    @Override
    public void init(GraphWindow graphWindow) {
        graphWindow.addNodeToList(AddFloat.class);
        graphWindow.addNodeToList(SubtractFloat.class);
        graphWindow.addNodeToList(MultiplyFloat.class);
        graphWindow.addNodeToList(DivideFloat.class);
        graphWindow.addNodeToList(PrintString.class);
    }
}
