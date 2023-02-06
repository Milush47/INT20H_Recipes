<template>
    <section class="recipes">
        <div class="container">
            <h1 class="title">
                Каталог рецептів
            </h1>

            <div class="filters">
            </div>


            <nav class="pager" aria-label="...">
                <ul class="pagination">
                    <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Попередня</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item active" aria-current="page">
                    <a class="page-link" href="#">2</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                    <a class="page-link" href="#">Наступна</a>
                    </li>
                </ul>
            </nav>
        </div>
    </section>
</template>

<script>
    export default {
        name: "CatalogPage"
    }
</script>

<style scoped>
@font-face { font-family: fixelMedium; src: url('@ast/fonts/MacPawFixelText-Medium.otf'); }
@font-face { font-family: fixelRegular; src: url('@ast/fonts/MacPawFixelText-Regular.otf'); }
@font-face { font-family: fixelBold; src: url('@ast/fonts/MacPawFixelText-Bold.otf'); }

.recipes {
    min-height: 87.5vh;
}

.title {
    font-family: fixelMedium;
    text-align: center;
    margin-top: 2.5vh;
}
.pager {
    display: flex;
    justify-content: center;
    display: none !important;
}
</style>