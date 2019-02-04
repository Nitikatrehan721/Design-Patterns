package adapter;

/*
 * using SquareToRectangleAdapter, we are able to adapt square to rectangle
 * we can have cache in adapter so, new time we can directly send the cached data, 
 * possible solution map<hashcode, data> i.e implement equals and hashcode
 */




class Square {
	public int side;

	public Square(int side) {
		this.side = side;
	}
}

interface Rectangle {
	int getWidth();

	int getHeight();

	default int getArea() {
		return getWidth() * getHeight();
	}
}

class SquareToRectangleAdapter implements Rectangle {
	private Square square;

	public SquareToRectangleAdapter(Square square) {
		this.square = square;
	}

	@Override
	public int getWidth() {
		return square.side;
	}

	@Override
	public int getHeight() {
		return square.side;
	}
}
