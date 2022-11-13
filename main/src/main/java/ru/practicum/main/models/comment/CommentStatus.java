package ru.practicum.main.models.comment;

/**
 * Enumeration of event {@link ru.practicum.main.models.event.Event} status
 * @author Eldar Gainanov
 */
public enum CommentStatus {
    /**
     * Comment posted by user
     */
    PUBLISHED,
    /**
     * Comment edited by user
     */
    EDITED,
    /**
     * Comment blocked by admin
     */
    BLOCKED
}
