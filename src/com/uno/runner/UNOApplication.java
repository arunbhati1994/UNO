package com.uno.runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.uno.beans.Card;
import com.uno.beans.Player;
import com.uno.service.impl.UnoDeckServiceImpl;


public class UNOApplication {

	public static void main(String[] args) {
		
		UnoDeckServiceImpl deck=new UnoDeckServiceImpl();
		
		List<Card> deckOfCards = deck.listOfCards();
		Collections.shuffle(deckOfCards);
		
		List<Card> cardList1=new ArrayList<Card>();
		List<Card> cardList2=new ArrayList<Card>();
		List<Card> cardList3=new ArrayList<Card>();
		List<Card> cardList4=new ArrayList<Card>();
		
		Iterator deckCardIterator=deckOfCards.iterator();
		
		//distribution of card
		deck.distibuteCardToOnePlayer(cardList1, deckCardIterator);
		deck.distibuteCardToOnePlayer(cardList2, deckCardIterator);
		deck.distibuteCardToOnePlayer(cardList3, deckCardIterator);
		deck.distibuteCardToOnePlayer(cardList4, deckCardIterator);
		
		List<Player> players=new ArrayList<Player>();
		players.add(new Player("Kathir", cardList1));
		players.add(new Player("Arun", cardList2));
		players.add(new Player("Varun", cardList3));
		players.add(new Player("Tarun", cardList4));
		
		ListIterator<Player> playingSequence = players.listIterator();
		
		deck.gameRunner(players, playingSequence,deckCardIterator);
	}

	
}
