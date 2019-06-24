package project.bookplanet;

import java.util.List;

import project.bookplanet.constants.KidFriendlyStatus;
import project.bookplanet.constants.UserType;
import project.bookplanet.controllers.BookmarkController;
import project.bookplanet.entities.Bookmark;
import project.bookplanet.entities.User;
import project.bookplanet.partner.Shareable;

// Represents UI
public class View {
	public static void browse(User user, List<List<Bookmark>> bookmarks) {
		System.out.println("\n" + user.getEmail() + " is browsing items ...");
		int bookmarkcount = 0;

		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				// Bookmarking!!
				//if (bookmarkcount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmarked = getBookmarkDecision(bookmark);
					if (isBookmarked) {
						bookmarkcount++;

						BookmarkController.getInstance().saveUserBookmark(user, bookmark);

						System.out.println("New Item Bookmarked -- " + bookmark);
					}
				//}

				
				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
				
					// Mark as Kid-friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN));
					KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
					if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
						BookmarkController.getInstance().setKidFriendlyStatus( user, kidFriendlyStatus, bookmark);
						
					}
				}
				
				// Sharing!!
				if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
						&& bookmark instanceof Shareable) {
					boolean isShared = getShareDecision();
					if(isShared) {
						BookmarkController.getInstance().share(user, bookmark);
					}
				}
			}
		}
	}
	
	// TODO: Below method simulate user input. After IO, we take input via console.
	private static boolean getShareDecision() {
		return Math.random() < 0.5 ? true : false;
		
	}

	private static KidFriendlyStatus getKidFriendlyStatusDecision(Bookmark bookmark) {
		double decision = Math.random();
		/*return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED
				: (Math.random() >= 0.4 && Math.random() < 0.8) ? KidFriendlyStatus.REJECTED
						: KidFriendlyStatus.UNKNOWN;*/
		return decision < 0.4 ? KidFriendlyStatus.APPROVED
				: (decision >= 0.4 && decision < 0.8) ? KidFriendlyStatus.REJECTED
						: KidFriendlyStatus.UNKNOWN;
	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;
	}

	/*
	 * public static void bookmark(User user, Bookmark[][] bookmarks) {
	 * System.out.println("\n" + user.getEmail() + " is bookmarking"); for (int i =
	 * 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) { int typeOffset =
	 * (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT); int bookmarkOffset =
	 * (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
	 * 
	 * Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
	 * 
	 * BookmarkController.getInstance().saveUserBookmark(user, bookmark);
	 * 
	 * System.out.println(bookmark); } }
	 */
}
