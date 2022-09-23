package neural;

public class InputUnit extends Unit {

	
	public void setInput(Double input) {
		this.netInput = input;
		this.output = input;
	}

}
