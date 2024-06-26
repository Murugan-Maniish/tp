package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTRIBUTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OUTLET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.article.Article;

/**
 * Adds an article to the article book.
 */
public class AddArticleCommand extends ArticleCommand {

    public static final String COMMAND_WORD = "add";

    public static final String COMMAND_PREFIX = "-a";

    // To be edited for use in test cases later on.
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_PREFIX
            + ": Adds an article to the article book.\n"
            + "Parameters: "
            + PREFIX_HEADLINE + "HEADLINE "
            + "[" + PREFIX_CONTRIBUTOR + "CONTRIBUTOR]... "
            + "[" + PREFIX_INTERVIEWEE + "INTERVIEWEE]... "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_OUTLET + "OUTLET]... "
            + PREFIX_DATE + "DATE "
            + PREFIX_STATUS + "STATUS "
            + "[" + PREFIX_LINK + "LINK]\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_PREFIX + " "
            + PREFIX_HEADLINE + "The Great Article "
            + PREFIX_CONTRIBUTOR + "John Doe "
            + PREFIX_INTERVIEWEE + "Jane Doe "
            + PREFIX_TAG + "friends "
            + PREFIX_OUTLET + "The Great Outlet "
            + PREFIX_DATE + "10-10-2024 "
            + PREFIX_STATUS + "DRAFT "
            + PREFIX_LINK + "https://www.example.com";

    public static final String MESSAGE_SUCCESS = "New article added: %1$s";
    public static final String MESSAGE_DUPLICATE_ARTICLE = "This article already exists in the article book";

    private final Article toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Article}
     */
    public AddArticleCommand(Article article) {
        requireNonNull(article);
        toAdd = article;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasArticle(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ARTICLE);
        }

        model.addArticle(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddArticleCommand)) {
            return false;
        }

        AddArticleCommand otherAddArticleCommand = (AddArticleCommand) other;
        return toAdd.equals(otherAddArticleCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
