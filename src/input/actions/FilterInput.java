package input.actions;

public class FilterInput {
    private SortFilter sort;
    private ContainsFilter contains;

    /**
     * Getter
     *
     * @return sorting filter
     */
    public SortFilter getSort() {
        return sort;
    }

    /**
     * Getter
     *
     * @return contains filter
     */
    public ContainsFilter getContains() {
        return contains;
    }
}
