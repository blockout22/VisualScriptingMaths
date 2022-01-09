package nodes;

import imgui.type.ImFloat;
import imgui.type.ImInt;
import imgui.type.ImString;
import visual.scripting.Graph;
import visual.scripting.NodeData;
import visual.scripting.Pin;
import visual.scripting.node.Node;

public class PrintString extends Node {

    private Pin execIn, execOut;
    private Pin pin_floatToString;

    public PrintString(Graph graph) {
        super(graph);
        setName("PrintString");
    }

    @Override
    public void init() {
        execIn = addInputPin(Pin.DataType.Flow, this);
        execOut = addOutputPin(Pin.DataType.Flow, this);

        pin_floatToString = addInputPin(Pin.DataType.Float, this);
    }

    @Override
    public void execute() {
        if(pin_floatToString.connectedTo != -1){
            Pin pin = getGraph().findPinById(pin_floatToString.connectedTo);
        }
    }

    @Override
    public String printSource(StringBuilder sb) {
        NodeData<ImFloat> inString = pin_floatToString.getData();

        String strOutput = "\"" + inString.value.get() + "\"";

        if(pin_floatToString.connectedTo != -1){
            Pin pin = getGraph().findPinById(pin_floatToString.connectedTo);
            strOutput = pin.getNode().printSource(sb);
        }

        sb.append("System.out.println(" + strOutput + ");\n");
        return "";
    }
}
