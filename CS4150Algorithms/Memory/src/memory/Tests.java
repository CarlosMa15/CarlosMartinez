package memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class was created to test the memory class
 * 
 * @author Carlos Martinez
 */
class Tests {

	@Test
	void TestCreate1() {
		Memory mem = new Memory();
		mem.create('F', "CS4400", "Home\\CS4400");
		mem.create('D', "Desktop", "Desktop");

		Entity entity = mem.getEntity("Home\\CS4400");
		Entity entity1 = mem.getEntity("Home");
		Entity entity2 = mem.getEntity("Desktop");

		assertEquals("Home\\CS4400", entity.getPath());
		assertEquals(0, entity.getSize());
		assertEquals(0, entity1.getSize());
		assertEquals(0, entity2.getSize());
	}

	@Test
	void TestCreate2() {
		Memory mem = new Memory();
		mem.create('F', "CS4400", "Home\\CS4400");

		Entity entity = mem.getEntity("Home");
		Entity entity1 = mem.getEntity("Home\\CS4400");

		assertEquals(0, entity.getSize());
		assertEquals(0, entity1.getSize());

		mem.create('T', "java", "Home\\CS4400\\java");

		Entity entity2 = mem.getEntity("Home");
		Entity entity3 = mem.getEntity("Home\\CS4400");
		Entity entity4 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, entity2.getSize());
		assertEquals(0, entity3.getSize());
		assertEquals(0, entity4.getSize());
	}

	@Test
	void TestDelete1() {
		Memory mem = new Memory();
		mem.create('F', "CS4400", "Home\\CS4400");
		mem.create('T', "java", "Home\\CS4400\\java");

		Entity actual = mem.getEntity("Home");
		Entity actual1 = mem.getEntity("Home\\CS4400");
		Entity actual2 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual.getSize());
		assertEquals(0, actual1.getSize());
		assertEquals(0, actual2.getSize());

		mem.delete("Home\\CS4400\\java");

		Entity actual3 = mem.getEntity("Home");
		Entity actual4 = mem.getEntity("Home\\CS4400");
		Entity actual5 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual3.getSize()); // Past
		assertEquals(0, actual4.getSize());
		assertNull(actual5);
	}

	@Test
	void TestDelete2() {
		Memory mem = new Memory();
		mem.create('F', "CS4400", "Home\\CS4400");
		mem.create('T', "java", "Home\\CS4400\\java");

		Entity actual = mem.getEntity("Home");
		Entity actual1 = mem.getEntity("Home\\CS4400");
		Entity actual2 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual.getSize());
		assertEquals(0, actual1.getSize());
		assertEquals(0, actual2.getSize());

		mem.delete("Home\\CS4400");

		Entity actual3 = mem.getEntity("Home");
		Entity actual4 = mem.getEntity("Home\\CS4400");
		Entity actual5 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual3.getSize()); // Past
		assertNull(actual4);
		assertNull(actual5);
	}

	@Test
	void TestMove1() {
		Memory mem = new Memory();
		mem.create('F', "CS4400", "Home\\CS4400");
		mem.create('T', "java", "Home\\CS4400\\java");
		mem.create('F', "CS4230", "Home\\CS4230");

		Entity actual = mem.getEntity("Home");
		Entity actual1 = mem.getEntity("Home\\CS4400");
		Entity actual2 = mem.getEntity("Home\\CS4400\\java");
		Entity actual3 = mem.getEntity("Home\\CS4230");

		assertEquals(0, actual.getSize());
		assertEquals(0, actual1.getSize());
		assertEquals(0, actual2.getSize());
		assertEquals(0, actual3.getSize());

		mem.move("Home\\CS4400\\java", "Home\\CS4230\\java");

		Entity actual4 = mem.getEntity("Home");
		Entity actual5 = mem.getEntity("Home\\CS4400");
		Entity actual6 = mem.getEntity("Home\\CS4400\\java");
		Entity actual7 = mem.getEntity("Home\\CS4230");
		Entity actual8 = mem.getEntity("Home\\CS4230\\java");

		assertEquals(0, actual4.getSize());
		assertEquals("Home", actual4.getPath());
		assertEquals(0, actual5.getSize());
		assertEquals("Home\\CS4400", actual5.getPath());
		assertNull(actual6);
		assertEquals(0, actual7.getSize());
		assertEquals("Home\\CS4230", actual7.getPath());
		assertEquals(0, actual8.getSize());
		assertEquals("Home\\CS4230\\java", actual8.getPath());
	}

	@Test
	void TestMove2() {
		Memory mem = new Memory();
		mem.create('F', "CS4400", "Home\\CS4400");
		mem.create('T', "java", "Home\\CS4400\\java");
		mem.create('F', "CS4230", "Home\\CS4230");

		Entity actual = mem.getEntity("Home");
		Entity actual1 = mem.getEntity("Home\\CS4400");
		Entity actual2 = mem.getEntity("Home\\CS4400\\java");
		Entity actual3 = mem.getEntity("Home\\CS4230");

		assertEquals(0, actual.getSize());
		assertEquals(0, actual1.getSize());
		assertEquals(0, actual2.getSize());
		assertEquals(0, actual3.getSize());

		mem.move("Home\\CS4400", "Home\\CS4230\\CS4400");

		Entity actual4 = mem.getEntity("Home");
		Entity actual5 = mem.getEntity("Home\\CS4400");

		Entity actual6 = mem.getEntity("Home\\CS4230\\CS4400\\java");
		Entity actual7 = mem.getEntity("Home\\CS4230");
		Entity actual8 = mem.getEntity("Home\\CS4230\\CS4400");
		Entity actual9 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual4.getSize());
		assertEquals("Home", actual4.getPath());
		assertNull(actual5);

		assertEquals(0, actual6.getSize());
		assertEquals("Home\\CS4230\\CS4400\\java", actual6.getPath());
		assertEquals(0, actual7.getSize());
		assertEquals("Home\\CS4230", actual7.getPath());
		assertEquals(0, actual8.getSize());
		assertEquals("Home\\CS4230\\CS4400", actual8.getPath());
		assertNull(actual9);
	}

	@Test
	void TestWriteTo1() {
		Memory mem = new Memory();
		mem.create('F', "CS4400", "Home\\CS4400");
		mem.create('T', "java", "Home\\CS4400\\java");

		Entity actual = mem.getEntity("Home");
		Entity actual1 = mem.getEntity("Home\\CS4400");
		Entity actual2 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual.getSize());
		assertEquals(0, actual1.getSize());
		assertEquals(0, actual2.getSize());

		mem.WriteToFile("Home\\CS4400\\java", "Carlos");

		Entity actual3 = mem.getEntity("Home");
		Entity actual4 = mem.getEntity("Home\\CS4400");
		Entity actual5 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(6, actual3.getSize());
		assertEquals(6, actual4.getSize());
		assertEquals(6, actual5.getSize());

		mem.WriteToFile("Home\\CS4400\\java", "Car");

		Entity actual6 = mem.getEntity("Home");
		Entity actual7 = mem.getEntity("Home\\CS4400");
		Entity actual8 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(3, actual6.getSize());
		assertEquals(3, actual7.getSize());
		assertEquals(3, actual8.getSize());

		mem.WriteToFile("Home\\CS4400\\java", "");

		Entity actual9 = mem.getEntity("Home");
		Entity actual10 = mem.getEntity("Home\\CS4400");
		Entity actual11 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual9.getSize());
		assertEquals(0, actual10.getSize());
		assertEquals(0, actual11.getSize());
	}

	@Test
	void TestDeleteWrite() {
		Memory mem = new Memory();
		mem.create('F', "CS4400", "Home\\CS4400");
		mem.create('T', "java", "Home\\CS4400\\java");

		Entity actual = mem.getEntity("Home");
		Entity actual1 = mem.getEntity("Home\\CS4400");
		Entity actual2 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual.getSize());
		assertEquals(0, actual1.getSize());
		assertEquals(0, actual2.getSize());

		mem.WriteToFile("Home\\CS4400\\java", "Carlos");

		Entity actual3 = mem.getEntity("Home");
		Entity actual4 = mem.getEntity("Home\\CS4400");
		Entity actual5 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(6, actual3.getSize());
		assertEquals(6, actual4.getSize());
		assertEquals(6, actual5.getSize());

		mem.delete("Home\\CS4400\\java");

		Entity actual6 = mem.getEntity("Home");
		Entity actual7 = mem.getEntity("Home\\CS4400");
		Entity actual8 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual6.getSize());
		assertEquals(0, actual7.getSize());
		assertNull(actual8);
	}

	@Test
	void TestZipFileSize1() {
		Memory mem = new Memory();
		mem.create('Z', "CS4400", "Home\\CS4400");
		mem.create('T', "java", "Home\\CS4400\\java");

		Entity actual = mem.getEntity("Home");
		Entity actual1 = mem.getEntity("Home\\CS4400");
		Entity actual2 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual.getSize());
		assertEquals(0, actual1.getSize());
		assertEquals(0, actual2.getSize());

		mem.WriteToFile("Home\\CS4400\\java", "Carlos");

		Entity actual3 = mem.getEntity("Home");
		Entity actual4 = mem.getEntity("Home\\CS4400");
		Entity actual5 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(3, actual3.getSize());
		assertEquals(3, actual4.getSize());
		assertEquals(6, actual5.getSize());

		mem.WriteToFile("Home\\CS4400\\java", "Carl");

		Entity actual6 = mem.getEntity("Home");
		Entity actual7 = mem.getEntity("Home\\CS4400");
		Entity actual8 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(2, actual6.getSize());
		assertEquals(2, actual7.getSize());
		assertEquals(4, actual8.getSize());

		mem.WriteToFile("Home\\CS4400\\java", "");

		Entity actual9 = mem.getEntity("Home");
		Entity actual10 = mem.getEntity("Home\\CS4400");
		Entity actual11 = mem.getEntity("Home\\CS4400\\java");

		assertEquals(0, actual9.getSize());
		assertEquals(0, actual10.getSize());
		assertEquals(0, actual11.getSize());
	}

	@Test
	void TestZipFileSize2() {
		Memory mem = new Memory();
		mem.create('Z', "CS4400", "Home\\CS4400");
		mem.create('Z', "CS4230", "Home\\CS4400\\CS4230");
		mem.create('T', "java", "Home\\CS4400\\CS4230\\java");

		Entity actual = mem.getEntity("Home");
		Entity actual1 = mem.getEntity("Home\\CS4400");
		Entity actual2 = mem.getEntity("Home\\CS4400\\CS4230");
		Entity actual3 = mem.getEntity("Home\\CS4400\\CS4230\\java");

		assertEquals(0, actual.getSize());
		assertEquals(0, actual1.getSize());
		assertEquals(0, actual2.getSize());
		assertEquals(0, actual3.getSize());

		mem.WriteToFile("Home\\CS4400\\CS4230\\java", "012345678910");

		Entity actual4 = mem.getEntity("Home");
		Entity actual5 = mem.getEntity("Home\\CS4400");
		Entity actual6 = mem.getEntity("Home\\CS4400\\CS4230");
		Entity actual7 = mem.getEntity("Home\\CS4400\\CS4230\\java");

		assertEquals(3, actual4.getSize());
		assertEquals(3, actual5.getSize());
		assertEquals(6, actual6.getSize());
		assertEquals(12, actual7.getSize());

		mem.WriteToFile("Home\\CS4400\\CS4230\\java", "012345678910012345678910");

		Entity actual8 = mem.getEntity("Home");
		Entity actual9 = mem.getEntity("Home\\CS4400");
		Entity actual10 = mem.getEntity("Home\\CS4400\\CS4230");
		Entity actual11 = mem.getEntity("Home\\CS4400\\CS4230\\java");

		assertEquals(6, actual8.getSize());
		assertEquals(6, actual9.getSize());
		assertEquals(12, actual10.getSize());
		assertEquals(24, actual11.getSize());

		mem.WriteToFile("Home\\CS4400\\CS4230\\java", "012345678910");

		actual4 = mem.getEntity("Home");
		actual5 = mem.getEntity("Home\\CS4400");
		actual6 = mem.getEntity("Home\\CS4400\\CS4230");
		actual7 = mem.getEntity("Home\\CS4400\\CS4230\\java");

		assertEquals(3, actual4.getSize());
		assertEquals(3, actual5.getSize());
		assertEquals(6, actual6.getSize());
		assertEquals(12, actual7.getSize());

		mem.WriteToFile("Home\\CS4400\\CS4230\\java", "");

		actual = mem.getEntity("Home");
		actual1 = mem.getEntity("Home\\CS4400");
		actual2 = mem.getEntity("Home\\CS4400\\CS4230");
		actual3 = mem.getEntity("Home\\CS4400\\CS4230\\java");

		assertEquals(0, actual.getSize());
		assertEquals(0, actual1.getSize());
		assertEquals(0, actual2.getSize());
		assertEquals(0, actual3.getSize());
	}
}