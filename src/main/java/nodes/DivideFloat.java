package nodes;

import imgui.type.ImFloat;
import visual.scripting.Graph;
import visual.scripting.NodeData;
import visual.scripting.Pin;
import visual.scripting.node.Node;

public class DivideFloat extends Node {

    private Pin inPin1, inPin2, outPin;

    public DivideFloat(Graph graph) {
        super(graph);
        setName("Divide Float");
    }

    @Override
    public void init() {
        inPin1 = addInputPin(Pin.DataType.Float, this);
        inPin2 = addInputPin(Pin.DataType.Float, this);

        outPin = addOutputPin(Pin.DataType.Float, this);

        String var = "DivideFloat" + getGraph().getNextLocalVariableID();
        outPin.setVariable(var);
    }

    @Override
    public void execute() {
        NodeData<ImFloat> data1 = inPin1.getData();
        NodeData<ImFloat> data2 = inPin2.getData();

        NodeData<ImFloat> outData = outPin.getData();
        outData.getValue().set(data2.getValue().get() == 0 ? 0 : data1.getValue().get() / data2.getValue().get());
    }

    @Override
    public String printSource(StringBuilder sb) {
        String toPrint = "";

        NodeData<ImFloat> data1 = inPin1.getData();
        NodeData<ImFloat> data2 = inPin2.getData();

        String input1 = String.valueOf(data1.value.get());
        String input2 = String.valueOf(data2.value.get());

        if(inPin1.connectedTo != -1){
            Pin pin = getGraph().findPinById(inPin1.connectedTo);
            input1 = pin.getNode().printSource(sb);
        }
        if(inPin2.connectedTo != -1){
            Pin pin = getGraph().findPinById(inPin2.connectedTo);
            input2 = pin.getNode().printSource(sb);
        }

        toPrint = "float " + outPin.getVariable() + " = " + input1 + " / " + input2 + ";";
        sb.append(toPrint + "\n");

        return outPin.getVariable();
    }
}
