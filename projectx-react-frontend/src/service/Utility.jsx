// Utility Functions

// chunk a collection in bits (chunks)
export const chunk = (array, size) => {

    if (array && Array.isArray(array)) {

        return array.reduce((chunks, item, i) => {
            if (i % size === 0) {
                chunks.push([item]);
            } else {
                chunks[chunks.length - 1].push(item);
            }
            return chunks;
        }, []);
    }
}