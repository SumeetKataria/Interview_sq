package org.test.readFiles;

import org.junit.Test;
import org.test.checker.CashCollectionCheckerMainClass;

public class CheckerTest {

	@Test
	public void checkerTest() {
		CashCollectionCheckerMainClass checker = new CashCollectionCheckerMainClass();
		checker.cashCollectionChecker();
	}
}
