package com.example.illusiondescontrastes;

enum Answer {
	LIGHTER ( -1 ),
	EQUALS ( 0 ),
	DARKER ( +1 );

	private final int value;

	int getValue() { return this.value; }

	Answer( int value ) { this.value = value; }
}
