package progam04;

public class TalkableTest {
	static void speak(Talkable a) {
		a.talk();
	}

	public static void main(String[] args) {
		speak(new Korean());
		speak(new American());
	}
}


