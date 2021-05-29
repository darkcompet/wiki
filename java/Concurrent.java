import java.util.concurrent.locks.ReentrantLock;

public class Concurrent {
	public static void main(String... args) {
		try {
			new Concurrent().foo();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	ReentrantLock rlock = new ReentrantLock();

	private void foo() throws Exception {
		rlock.lock();
		try {
			//
		}
		finally {
			rlock.unlock();
		}
	}
}
