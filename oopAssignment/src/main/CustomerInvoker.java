package main;


public class CustomerInvoker {
	public <T> T executeCommand(Command<T> command) {
		return command.execute();
	}
}
