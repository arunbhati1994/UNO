package com.uno.beans;

import java.util.List;

import com.uno.beans.Card;


public class Player {
	private String name;
	private List<Card> cardList;
	
	public Player() {
	}
	
	public Player(String name, List<Card> cardList) {
		this.name = name;
		this.cardList = cardList;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getCardList() {
		return cardList;
	}
	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", cardList=" + cardList + "]";
	}
	
	
	
}
