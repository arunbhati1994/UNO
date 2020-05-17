package com.uno.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.uno.beans.Card;
import com.uno.beans.Player;
import com.uno.enums.CardType;
import com.uno.exceptions.IllegalCardThrownException;
import com.uno.service.UnoDeckService;

public class UnoDeckServiceImpl implements UnoDeckService {
	@Override
	public void distibuteCardToOnePlayer(List<Card> cardList1, Iterator itr) {
		while (itr.hasNext()) {

			if (cardList1.size() <= 4) {
				cardList1.add((Card) itr.next());
				itr.remove();
			} else {
				break;
			}
		}
		System.out.println(cardList1);
	}

	@Override
	public void gameRunner(List<Player> players,
			ListIterator<Player> playingSequence, Iterator deckCardIterator) {
		Player currentPlayer = null;
		Card currentCard = null;

		currentPlayer = playingSequence.next();
		currentCard = firstPlayerChooseFirstCard(currentPlayer); // first player
																	// - Game
																	// started
		gameCycle(players, playingSequence, currentPlayer, currentCard,deckCardIterator);
		players.stream().filter(p -> p.getCardList().size() == 0)
				.map(p -> "winner is :: " + p.getName())
				.forEach(System.out::println);
	}

	private void gameCycle(List<Player> players,
			ListIterator<Player> playingSequence, Player currentPlayer,
			Card currentCard,Iterator deckCardIterator) {
		int clockwise = 1; // AntiClockwise=1,ClockWise=0
		while (players.stream().filter(p -> p.getCardList().size() == 0)
				.count() != 1) { // will run till one of them have zero card
									// left
			switch (clockwise) {
			case 1: // antiClockWise
				if (currentCard.getValue().equals("reverse")) {
					clockwise = 0;// changing cycle flow
					break;
				}
				if (currentCard.getValue().equals("skip")) {
					resetForwardIteratorWhenReachEnd(players, playingSequence);
					playingSequence.next(); // skipping one player
				}
				if (currentCard.getValue().equals("Plus Four")) {
					resetForwardIteratorWhenReachEnd(players, playingSequence);
					List<Card> fourCardListFromDeck=new LinkedList<Card>();
					fourCardListFromDeck.add((Card) deckCardIterator.next());
					fourCardListFromDeck.add((Card) deckCardIterator.next());
					fourCardListFromDeck.add((Card) deckCardIterator.next());
					fourCardListFromDeck.add((Card) deckCardIterator.next());
					
					playingSequence.next().getCardList().addAll(fourCardListFromDeck);
				}
				

				playingSequence = resetForwardIteratorWhenReachEnd(players,
						playingSequence);

				currentPlayer = playingSequence.next();
				currentCard = currentPlayerChooseNextCard(currentPlayer,currentCard);
				
				
				break;
			case 0: // ClockWise
				if (!playingSequence.hasPrevious()) {
					Collections.reverse(players);
					playingSequence = players.listIterator();
					clockwise = 1;
					break;
				}
				if (playingSequence.hasPrevious()) {

					if (currentCard.getValue().equals("reverse")) {
						Player playerCheck = playingSequence.previous();
					}
					if (currentCard.getValue().equals("skip")) {
						playingSequence.previous();
					}
					if (playingSequence.hasPrevious()) {
						currentPlayer = playingSequence.previous();
						currentPlayerChooseNextCard(currentPlayer, currentCard);
					}
					
				}
			}
		}
	}

	private ListIterator<Player> resetForwardIteratorWhenReachEnd(
			List<Player> players, ListIterator<Player> playingSequence) {
		if (!playingSequence.hasNext()) {
			playingSequence = players.listIterator();

		}
		return playingSequence;
	}

	@Override
	public Card firstPlayerChooseFirstCard(Player currentPlayer) {

		Card selectedCard = cardInputScanner(currentPlayer);
		System.out.println(selectedCard + "::::::");
		currentPlayer.getCardList().remove(selectedCard);
		return selectedCard;
	}

	private Card cardInputScanner(Player currentPlayer) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Current Player Name : " + currentPlayer.getName()
				+ "  Available Cards ::  " + "\n");
		currentPlayer.getCardList().stream().forEach(System.out::println);
		int selectedCardPlace = 0;
		try {
			selectedCardPlace = scan.nextInt() - 1;
		} catch (Exception e) {
			System.out.println("Select the available Cards Only");
		}
		Card selectedCard = currentPlayer.getCardList().get(selectedCardPlace);

		return selectedCard;
	}

	@Override
	public Card currentPlayerChooseNextCard(Player currentPlayer,
			Card currentCard) {
		// taking input from Player ***************************

		Card selectedCard = cardInputScanner(currentPlayer);

		System.out.println(currentCard + "::::::" + selectedCard);
		if (!currentCard.getValue().equals(selectedCard.getValue()))
			if (!currentCard.getDeck().equals(selectedCard.getDeck()))
				if (!currentCard.getValue().equals("Colour Change")
						|| !currentCard.getValue().equals("Plus Four")) {
					try {
						throw new IllegalCardThrownException(
								"Illegal Card Thrown");
					} catch (IllegalCardThrownException e) {
						e.printStackTrace();
					}
				} else {
					currentPlayer.getCardList().remove(selectedCard);
				}
		currentCard = selectedCard;
		System.out.println("Current card :: " + currentCard);
		return currentCard;
	}

	@Override
	public List<Card> listOfCards() {

		Set<Card> setOfRandomCards = new HashSet<Card>();
		CardType deck = null;
		cardListgenerator(setOfRandomCards, deck); // generate 108 cards in dck
		return setOfRandomCards.stream().collect(Collectors.toList());

	}

	private void cardListgenerator(Set<Card> setOfRandomCards, CardType deck) {
		String value;
		for (int i = 1; i <= 8; ++i) { // Generating WIldCards - count -8
			if (i % 2 == 0) {
				if (i == 1)
					deck = CardType.COLOURCHANGE;
				value = "Colour Change";
				setOfRandomCards.add(new Card(value, deck));
			}
			if (i % 2 != 0) {
				if (i == 2)
					deck = CardType.PLUSFOUR;
				value = "Plus Four";
				setOfRandomCards.add(new Card(value, deck));
			}
		}

		for (int i = 1; i <= 4; ++i) { // Generating 4 Color ards - count -100
			if (i == 1)
				deck = CardType.BLUE;
			if (i == 2)
				deck = CardType.RED;
			if (i == 3)
				deck = CardType.GREEN;
			if (i == 4)
				deck = CardType.YELLOW;

			for (int j = 0; j <= 15; j++) {
				if (j == 0) {
					setOfRandomCards.add(new Card("0", deck));
				}
				if (j <= 9 && j > 0) {
					setOfRandomCards.add(new Card("" + j, deck));
					setOfRandomCards.add(new Card("" + j, deck));
				}
				if (j == 10 || j == 11) {
					value = "plusTwo";
					setOfRandomCards.add(new Card(value, deck));
				}
				if (j == 12 || j == 13) {
					value = "reverse";
					setOfRandomCards.add(new Card(value, deck));
				}
				if (j == 14 || j == 15) {
					value = "skip";
					setOfRandomCards.add(new Card(value, deck));
				}
			}
		}
	}

}
