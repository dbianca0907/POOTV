package input.actions;

public class FilterInput {
    private SortFilter sort;
    private ContainsFilter contains;

    /**
     * getter
     * @return
     */
    public SortFilter getSort() {
        return sort;
    }
    /**
     * getter
     * @return
     */
    public ContainsFilter getContains() {
        return contains;
    }

    /**
     * setter
     * @param sort
     */
    public void setSort(final SortFilter sort) {
        this.sort = sort;
    }

    /**
     * setter
     * @param contains
     */
    public void setContains(final ContainsFilter contains) {
        this.contains = contains;
    }
}
