function getTagsArray(str) {
    return str.split(' ').filter(value=> value.includes('#')).map(value => value.replace('#', ''));
}
function countTags(tags) {
    let obj = {};
    tags.forEach(element => Object.keys(obj).includes(element) ? obj[element] += 1 : obj[element] = 1);
    return obj;
}
function addTags(oldTags, newTags) {
    for(let newTag in newTags){
        for(let objTag in oldTags) {
            if(objTag === newTag) {
                oldTags[objTag] += newTags[newTag];
                break;
            }
        }
        if(!Object.keys(oldTags).includes(newTag)) oldTags[newTag] = newTags[newTag];
    }
    return sortTags(oldTags);
}
export function getAllTags(tagsArr){
    let allTags = tagsArr[0]; 
    for(let i = 1; i<tagsArr.length; i++) {
        allTags = addTags(allTags, tagsArr[i]);
    }
    return allTags;
}
function sortTags(obj) {
    let sorted = {};
    let values = Object.keys(obj).map(element => obj[element]).sort((a, b) => b-a);
    for(let value of values){
        for(let element in obj) {
            if(obj[element] === value && !Object.keys(sorted).includes(value)) sorted[element] = value;
        }
    }
    return sorted;
}
export function getSortedTags(str) {
    return sortTags(countTags(getTagsArray(str)));
}

