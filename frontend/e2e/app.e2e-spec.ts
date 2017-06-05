import { ExtractViewerPage } from './app.po';

describe('extract-viewer App', () => {
  let page: ExtractViewerPage;

  beforeEach(() => {
    page = new ExtractViewerPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
