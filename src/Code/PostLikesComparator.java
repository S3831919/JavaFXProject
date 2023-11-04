//reference https://www.geeksforgeeks.org/how-to-sort-an-arraylist-of-objects-by-property-in-java/
package Code;

import java.util.Comparator;

import application.Post;

public class PostLikesComparator implements Comparator<Post> {
    @Override
    public int compare(Post post1, Post post2) {
        return Integer.compare(post2.getPostLikes(), post1.getPostLikes()); // Sort in descending order
    }
}