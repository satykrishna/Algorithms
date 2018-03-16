package example.ds.algos.test;

public class Test {

	public static void test2Dijkstras() {

		MyGraph graph = new MyGraph(50);

		Vertex a = new Vertex('A', 0);

		Vertex b = new Vertex('B', 1);

		Vertex c = new Vertex('C', 2);

		Vertex d = new Vertex('D', 3);

		Vertex e = new Vertex('E', 4);

		Vertex f = new Vertex('F', 5);

		Vertex g = new Vertex('G', 6);

		Vertex[] vertices = new Vertex[] { a, b, c, d, e, f, g };

		graph.addVertices(vertices);

		graph.addEdgeWithWeight(a, b, 2);

		graph.addEdgeWithWeight(a, d, 5);

		graph.addEdgeWithWeight(b, d, 4);

		graph.addEdgeWithWeight(b, e, 3);

		graph.addEdgeWithWeight(c, a, 1);

		graph.addEdgeWithWeight(c, f, 2);

		graph.addEdgeWithWeight(d, f, 3);

		graph.addEdgeWithWeight(d, g, 3);

		graph.addEdgeWithWeight(e, g, 4);

		graph.addEdgeWithWeight(f, g, 2);

		graph.addEdgeWithWeight(b, c, 2);

		graph.dijkstraAlgos(b);
	}

	public static void test2BellmanFord() {

		MyGraph graph = new MyGraph(50);

		Vertex a = new Vertex('A', 0);

		Vertex b = new Vertex('B', 1);

		Vertex c = new Vertex('C', 2);

		Vertex d = new Vertex('D', 3);

		Vertex[] vertices = new Vertex[] { a, b, c, d };

		graph.addVertices(vertices);

		graph.addEdgeWithWeight(a, b, 1);

		graph.addEdgeWithWeight(b, d, -6);

		graph.addEdgeWithWeight(d, c, 2);

		graph.addEdgeWithWeight(c, b, 3);

		graph.bellmanFordAlgorithm(a);

	}

	public static void test1BellmanFord() {

		MyGraph graph = new MyGraph(50);

		Vertex a = new Vertex('A', 0);

		Vertex b = new Vertex('B', 1);

		Vertex c = new Vertex('C', 2);

		Vertex d = new Vertex('D', 3);

		Vertex e = new Vertex('E', 4);

		Vertex[] vertices = new Vertex[] { a, b, c, d, e };

		graph.addVertices(vertices);

		graph.addEdgeWithWeight(a, d, 8);

		graph.addEdgeWithWeight(a, b, 4);

		graph.addEdgeWithWeight(a, c, 5);

		graph.addEdgeWithWeight(b, c, -3);

		graph.addEdgeWithWeight(c, e, 4);

		graph.addEdgeWithWeight(d, e, 2);

		graph.addEdgeWithWeight(e, d, 1);

		graph.bellmanFordAlgorithm(a);
	}

	public static void test1Dijkstras() {

		MyGraph graph = new MyGraph(50);

		Vertex a = new Vertex('A', 0);

		Vertex b = new Vertex('B', 1);

		Vertex c = new Vertex('C', 2);

		Vertex d = new Vertex('D', 3);

		Vertex e = new Vertex('E', 4);

		// Vertex f = new Vertex('F', 5);
		//
		// Vertex g = new Vertex('G', 6);

		Vertex[] vertices = new Vertex[] { a, b, c, d, e };

		graph.addVertices(vertices);

		graph.addEdgeWithWeight(a, b, 4);

		graph.addEdgeWithWeight(a, c, 1);

		graph.addEdgeWithWeight(c, b, 2);

		graph.addEdgeWithWeight(c, d, 4);

		graph.addEdgeWithWeight(b, e, 4);

		graph.addEdgeWithWeight(d, e, 4);

		// graph.addEdge(e, g);

		// graph.addEdge(g, f);

		// graph.addEdge(c, f);

		// graph.addEdge(c, a);

		// graph.addEdge(a, d);

		// graph.addEdge(b, d);

		// graph.addEdge(d, f);

		// graph.addEdge(d, g);

		graph.dfs();

		System.out.println("________________\n");

		graph.bfs();

		System.out.println("___________________\n");

		// graph.shortestPathinUnWeightedGraph(b);

		graph.dijkstraAlgos(a);

	}

	private static void testSimplePath() {
		MyGraph graph = new MyGraph(50);

		Vertex a = new Vertex('A', 0);

		Vertex b = new Vertex('B', 1);

		Vertex c = new Vertex('C', 2);

		Vertex d = new Vertex('D', 3);

		Vertex e = new Vertex('E', 4);

		Vertex f = new Vertex('F', 5);

		Vertex g = new Vertex('G', 6);

		Vertex[] vertices = new Vertex[] { a, b, c, d, e, f, g };

		graph.addVertices(vertices);

		graph.addEdgeWithWeight(a, b, 2);

		graph.addEdgeWithWeight(a, d, 5);

		graph.addEdgeWithWeight(b, d, 4);

		graph.addEdgeWithWeight(b, e, 3);

		graph.addEdgeWithWeight(c, a, 1);

		graph.addEdgeWithWeight(c, f, 2);

		graph.addEdgeWithWeight(d, f, 3);

		graph.addEdgeWithWeight(d, g, 3);

		graph.addEdgeWithWeight(e, g, 4);

		graph.addEdgeWithWeight(f, g, 2);

		graph.addEdgeWithWeight(b, c, 2);
		
		graph.simplePath(d, g);

		graph.countSimplePaths(d, g);
	
	}

	

	private static void test2SimplePath() {
		MyGraph graph = new MyGraph(50);

		Vertex a = new Vertex('A', 0);

		Vertex b = new Vertex('B', 1);

		Vertex c = new Vertex('C', 2);

		Vertex d = new Vertex('D', 3);

		Vertex e = new Vertex('E', 4);		
		
		Vertex g = new Vertex('G', 5);
		
		Vertex[] vertices = new Vertex[] { a, b, c, d, e, g };

		graph.addVertices(vertices);
		
		graph.addEdge(a, c);
		
		graph.addEdge(a, b);
		
		graph.addEdge(c, b);
		
		graph.addEdge(c, d);
		
		graph.addEdge(d, e);
		
		graph.addEdge(b, e);
		
		graph.addEdge(d, g);
		
		graph.addEdge(g, e);
		
		graph.addEdge(c, a);
		
		graph.addEdge(d, a);
		
		graph.addEdge(a, d);
		
		graph.simplePath(c, e);
		
		
		graph.countSimplePaths(c, e);
		
		System.out.println(graph.countSimplePaths(c, a, 2));
		
	}

	
	private static void testSCC() {
		
		MyGraph graph = new MyGraph(50);

		Vertex a = new Vertex('A', 0);

		Vertex b = new Vertex('B', 1);

		Vertex c = new Vertex('C', 2);

		Vertex d = new Vertex('D', 3);
		
		Vertex[] vertices = new Vertex[] {a, b, c, d};
		
		graph.addVertices(vertices);
		
		graph.addEdge(a, b);
		
		graph.addEdge(b, c);
		
		graph.addEdge(c, a);
		
		graph.addEdge(c, d);
		
		graph.addEdge(a, d);
		
		graph.sccsUsingBFS();
	}
	
	private static void testSCC2() {
		MyGraph graph = new MyGraph(50);

		Vertex a = new Vertex('A', 0);

		Vertex b = new Vertex('B', 1);

		Vertex c = new Vertex('C', 2);

		Vertex d = new Vertex('D', 3);

		Vertex e = new Vertex('E', 4);		
		
		Vertex f = new Vertex('F', 5);
		
		Vertex g = new Vertex('G', 6);
		
		Vertex h = new Vertex('H', 7);

		Vertex i = new Vertex('I', 8);

		Vertex j = new Vertex('J', 9);
		
		Vertex k = new Vertex('K', 10);


		
		Vertex[] vertices = new Vertex[] { a, b, c, d, e, f, g, h, i, j, k };

		graph.addVertices(vertices);
		
		graph.addEdge(a, b);
		
		graph.addEdge(b, c);
		
		graph.addEdge(c, a);
		
		graph.addEdge(b, d);
		
		graph.addEdge(d, e);
		
		graph.addEdge(e, f);
		
		graph.addEdge(f, d);
		
		graph.addEdge(g, h);
		
		graph.addEdge(g, f);
		
		graph.addEdge(h, i);
		
		graph.addEdge(i, j);
		
		graph.addEdge(j, g);
		
		graph.addEdge(j, k);
		
		graph.sccsUsingBFS();
		
		
		
	}


	public static void main(String[] args) {
		// test1Dijkstras();
		// test2Dijkstras();
		// test1BellmanFord();
		// test2BellmanFord();
//		testSimplePath();
//		test2SimplePath();
		
//		testSCC();
		
		testSCC2();

	}

	


}
