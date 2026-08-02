<script lang="ts">
    import {createEventDispatcher} from "svelte";

    let value = "";

    const dispatch = createEventDispatcher<{
        search: { query: string }
    }>();
</script>

<div class="search-wrapper">
    <input class="search" type="text" placeholder="Search..." bind:value={value}
           on:input={() => dispatch("search", {query: value})} spellcheck="false">
</div>

<style lang="scss">

  .search-wrapper {
    position: relative;
    flex: 1;

    &::before {
      content: "";
      position: absolute;
      left: 20px;
      top: 50%;
      transform: translateY(-50%);
      width: 22px;
      height: 22px;
      background-color: var(--menu-text-color);
      -webkit-mask-image: url("/img/menu/icon-search.svg");
      mask-image: url("/img/menu/icon-search.svg");
      -webkit-mask-repeat: no-repeat;
      mask-repeat: no-repeat;
      -webkit-mask-position: center;
      mask-position: center;
    }
  }

  .search {
    background-color: var(--menu-search-background-color);
    border: none;
    color: var(--menu-text-color);
    font-family: "Inter", sans-serif;
    font-size: 20px;
    border-radius: 5px;
    border-bottom: solid 4px var(--menu-search-border-color);
    padding: 20px 20px 20px 60px;
    width: 100%;
  }
</style>