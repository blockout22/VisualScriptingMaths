package nodes;

import imgui.type.ImFloat;
import imgui.type.ImString;
import visual.scripting.Graph;
import visual.scripting.NodeData;
import visual.scripting.node.Node;
import visual.scripting.pin.Pin;
import visual.scripting.pin.PinFloat;
import visual.scripting.pin.PinFlow;

public class PrintString extends Node {

    private Pin execIn, execOut;
    private Pin pin_floatToString;

    public PrintString(Graph graph) {
        super(graph);
        setCategory("Maths");
        setName("PrintString");
    }

    @Override
    public void init() {
        execIn = new PinFlow();
        execIn.setNode(this);
        addCustomInput(execIn);

        pin_floatToString = new PinFloat();
        pin_floatToString.setNode(this);
        addCustomInput(pin_floatToString);

        execOut = new PinFlow();
        execOut.setNode(this);
        addCustomOutput(execOut);
    }

    @Override
    public void execute() {
        if(pin_floatToString.connectedTo != -1){
            Pin pin = getGraph().findPinById(pin_floatToString.connectedTo);

            NodeData<ImFloat> connectedData = pin.getData();
            NodeData<ImFloat> inData = pin_floatToString.getData();

            inData.getValue().set(connectedData.getValue().get());
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
