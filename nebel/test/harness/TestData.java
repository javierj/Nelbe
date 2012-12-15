package harness;

import org.iwt2.nebel.model.Sizes;
import org.iwt2.nebel.model.World;

public class TestData {
	
	public static World createWorld320x320() {
		World w = new World(createSizes320x320());
		return w;
	}

	public static Sizes createSizes320x320() {
		Sizes ss = Sizes.getInstance();
		ss.setWithAndHeight(320, 320);
		ss.setBlockAndArrowButtonSize(16, 16);
		return ss;
	}
}
