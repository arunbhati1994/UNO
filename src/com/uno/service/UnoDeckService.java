package com.uno.service;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.uno.beans.Card;
import com.uno.beans.Player;
import com.uno.exceptions.IllegalCardThrownException;

public interface UnoDeckService {


	public void distibuteCardToOnePlayer(List<Card> cardList1, Iterator itr);
	public void gameRunner(List<Player> players,ListIterator<Player> playingSequence,Iterator deckCardIterator);
	public Card currentPlayerChooseNextCard(Player CurrentPlayer, Card currentCard);
	public List<Card> listOfCards();
	Card firstPlayerChooseFirstCard(Player currentPlayer);

}
