import request from "axios";

const server = "http://localhost:8080/";

export const updateOne = async (link, data) => {
    try {
        return await request.put(server + link, data);
    } catch (e) {
        const entity = link.replace(/\/.+/, "");
        if (e.response.status === 400) {
            const res = {};
            const messages = e.response.data.message;
            for (let message of messages) {
                const split = message.split(" ");
                const splitName = split[0].split(".");
                const name = splitName.length === 3 ? splitName[2] : splitName[0];
                split[0] = fieldNames[entity][name];
                res[name] = split.join(" ");
            }
            throw new Error(JSON.stringify(res));
        } else {
            e.message = e.response.data.message;
            throw e;
        }
    }
};

export const createOne = async (link, data) => {
    try {
        return await request.post(server + link, data);
    } catch (e) {
        const entity = link.replace(/\/.+/, "");
        if (e.response.status === 400) {
            const res = {};
            const messages = e.response.data.message;
            for (let message of messages) {
                const split = message.split(" ");
                const splitName = split[0].split(".");
                const name = splitName.length === 3 ? splitName[2] : splitName[0];
                split[0] = fieldNames[entity][name];
                res[name] = split.join(" ");
            }
            throw new Error(JSON.stringify(res));
        } else {
            e.message = e.response.data.message;
            throw e;
        }
    }
};