package com.uno.beans;

import com.uno.enums.CardType;

public class Card  {
	private String Value;
	private CardType deck;
	
	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Card(String value, CardType deck) {
		super();
		Value = value;
		this.deck = deck;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public CardType getDeck() {
		return deck;
	}

	public void setDeck(CardType deck) {
		this.deck = deck;
	}

	@Override
	public String toString() {
		return "Card [Value=" + Value + ", deck=" + deck + "]";
	}
	
	
}
