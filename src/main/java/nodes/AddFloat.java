package nodes;

import imgui.type.ImFloat;
import visual.scripting.Graph;
import visual.scripting.NodeData;
import visual.scripting.node.Node;
import visual.scripting.pin.Pin;
import visual.scripting.pin.PinFloat;

public class AddFloat extends Node {

    private Pin inPin1, inPin2, outPin;

    public AddFloat(Graph graph) {
        super(graph);
        setCategory("Maths");
        setName("Add Float");
    }

    @Override
    public void init() {
        inPin1 = new PinFloat();
        inPin1.setNode(this);
        addCustomInput(inPin1);

        inPin2 = new PinFloat();
        inPin2.setNode(this);
        addCustomInput(inPin2);

        outPin = new PinFloat();
        outPin.setNode(this);
        addCustomOutput(outPin);

        String var = "AddFloat" + getGraph().getNextLocalVariableID();
        outPin.setVariable(var);
    }

    @Override
    public void execute() {
        NodeData<ImFloat> data1 = inPin1.getData();
        NodeData<ImFloat> data2 = inPin2.getData();

        if(inPin1.connectedTo != -1){
            Pin connectedTo = getGraph().findPinById(inPin1.connectedTo);
            NodeData<ImFloat> connectedData = connectedTo.getData();
            data1.getValue().set(connectedData.getValue().get());
        }

        if(inPin2.connectedTo != -1){
            Pin connectedTo = getGraph().findPinById(inPin2.connectedTo);
            NodeData<ImFloat> connectedData = connectedTo.getData();
            data2.getValue().set(connectedData.getValue().get());
        }

        NodeData<ImFloat> outData = outPin.getData();
        outData.getValue().set(data1.getValue().get() + data2.getValue().get());
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

        toPrint = "float " + outPin.getVariable() + " = " + input1 + " + " + input2 + ";";
        sb.append(toPrint + "\n");

        return outPin.getVariable();
    }
}
