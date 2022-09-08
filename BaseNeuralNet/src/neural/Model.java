package neural;

import java.util.Arrays;

public class Model {
	
	public static void main(String[] args) {
		Unit[] units = new Unit[3];
		
		for(int i = 0; i<3;i++) {
			units[i] = new InputUnit();
		}
		
		FirstLayer first = new FirstLayer("IN", units);
		Layer second = new Layer(8, "HID_1", Activation.RELU, first);
		Layer third = new Layer(5, "HID_2", Activation.RELU, second);
		Layer out = new Layer(1, "OUT", Activation.SIGMOID, third);
		
		//Setting the input
		((InputUnit) first.neurons[0]).setInput(5.0);
		((InputUnit) first.neurons[1]).setInput(0.0);
		((InputUnit) first.neurons[2]).setInput(3.0);
		
		//Computing the layers
		second.computeLayer();
		System.out.println("Hidden1");
		Arrays.asList(second.neurons).forEach(n -> {
			System.out.println(Arrays.toString(((Neuron)n).weights));
		});
		third.computeLayer();
		System.out.println("Hidden2");
		Arrays.asList(third.neurons).forEach(n -> {
			System.out.println(Arrays.toString(((Neuron)n).weights));
		});
		out.computeLayer();
		System.out.println("Out");
		Arrays.asList(out.neurons).forEach(n -> {
			System.out.println(Arrays.toString(((Neuron)n).weights));
		});
		
		System.out.println("Net Input " + out.neurons[0].netInput);
		System.out.println("Result: " +out.neurons[0].output);
	}

}
