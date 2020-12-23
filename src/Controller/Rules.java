package Controller;

public class Rules
{
	Board board;
	
	public Rules(Board board1)
	{
		board = board1;
	}
	
	void red_rules()
	{
		if (board.redPoint[0] - board.bluePoint[0] >= 8)
		{
			board.msg("RED Wins");
		}
		
		if ((board.redPoint[1] != 4) && (board.redPoint[2] != 4))
			return;
		
		board.msg("RED disqualified.");
		return;
	}

	void blue_rules()
	{
		if (board.bluePoint[0] - board.redPoint[0] >= 8)
		{
			board.msg("BLUE Wins");
		}
		
		if ((board.bluePoint[1] != 4) && (board.bluePoint[2] != 4))
			return;
		
		board.msg("BLUE disqualified.");
		return;
	}

	// ......................... \\ 
	
	public void redPoint(int value)
	{
		board.redPoint[0] += value;

		board.red_point.setText(""+board.redPoint[0]+"");

		red_rules();
	}

	void redCat1()
	{
		board.redPoint[1] += 1;

		board.cat1_red_show.append("X");

		if (board.redPoint[1] == 3)
		{
			// board.bluePoint[0] += 1; // 2 Antal point en straf (Hansoku chui) udl�ser
			// board.blue_point.setText(""+board.bluePoint[0]+"");
			// board.msg("Penalty RED Athlete");
			return;
		}
		if (board.redPoint[1] == 2)
		{
			// board.bluePoint[0] += 1;
			// board.blue_point.setText(""+board.bluePoint[0]+"");
			// board.msg("Penalty RED Athlete");
			return;
		}

		red_rules();
	}

	void redCat2()
	{
		board.redPoint[2] += 1;

		board.cat2_red_show.append("X");

		if (board.redPoint[2] == 3)
		{
			// board.bluePoint[0] += 1; // 2 Antal point en straf (Hansoku chui) udl�ser
			// board.blue_point.setText(""+board.bluePoint[0]+"");
			// board.msg("Penalty RED Athlete");
			return;
		}
		if (board.redPoint[2] == 2)
		{
			// board.bluePoint[0] += 1;
			// board.blue_point.setText(""+board.bluePoint[0]+"");
			// board.msg("Penalty RED Athlete");
			return;
		}

		red_rules();
	}

	void bluePoint(int value)
	{
		board.bluePoint[0] += value;

		board.blue_point.setText(""+board.bluePoint[0]+"");

		blue_rules();
	}

	void blueCat1()
	{
		board.bluePoint[1] += 1;

		board.cat1_blue_show.append("X");

		if (board.bluePoint[1] == 3)
		{
			// board.redPoint[0] += 1; // 2 Antal point en straf (Hansoku chui) udl�ser
			// board.red_point.setText(""+board.redPoint[0]+"");
			// board.msg("Penalty BLUE Athlete");
			return;
		}
		if (board.bluePoint[1] == 2)
		{
			// board.redPoint[0] += 1;
			// board.red_point.setText(""+board.redPoint[0]+"");
			// board.msg("Penalty BLUE Athlete");
			return;
		}

		blue_rules();
	}

	void blueCat2()
	{
		board.bluePoint[2] += 1;

		board.cat2_blue_show.append("X");

		if (board.bluePoint[2] == 3)
		{
			// board.redPoint[0] += 1; // 2 Antal point en straf (Hansoku chui) udl�ser
			// board.red_point.setText(""+board.redPoint[0]+"");
			// board.msg("Penalty BLUE Athlete");
			return;
		}
		if (board.bluePoint[2] == 2)
		{
			// board.redPoint[0] += 1;
			// board.red_point.setText(""+board.redPoint[0]+"");
			// board.msg("Penalty BLUE Athlete");
			return;
		}

		blue_rules();
	}	
	
}
